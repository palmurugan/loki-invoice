import React, { Component } from 'react';
import axios from 'axios';

import { lighten, makeStyles } from '@material-ui/core/styles';
import {
    Tooltip, Button, IconButton, Toolbar, Typography, Paper,
    TableRow, TableHead, TableContainer, TableCell, TablePagination, Table,
    Dialog, DialogTitle, DialogContent, DialogActions, DialogContentText
} from '@material-ui/core';
import AddIcon from '@material-ui/icons/Add';
import ImportExportIcon from '@material-ui/icons/ImportExport';
import FilterListIcon from '@material-ui/icons/FilterList';

import { DynamicBody } from './components';

import {
    Link
} from "react-router-dom";

/**
 * @author Palmurugan C
 */
class DataGrid extends Component {
    constructor(props) {
        super(props);

        this.state = {
            dynamicData: [],
            totalCount: 0,
            page: 0,
            rowsPerPage: 10,
            enableDialog: false,
            deleteId: 0
        }
        this.handleChangePage = this.handleChangePage.bind(this);
        this.deleteOperation = this.deleteOperation.bind(this);
        this.handleClose = this.handleClose.bind(this);
        this.handleSuccess = this.handleSuccess.bind(this);
    }

    componentDidMount() {
        this.loadData();
    }

    loadData() {
        const additionalParam = this.props.additionalParam ? this.props.additionalParam : '';
        axios.get(this.props.apiName + '?page=0&size=10&sort=id,desc' + additionalParam).then(res => {
            this.setState({ totalCount: parseInt(res.headers['x-total-count']) });
            this.setState({ dynamicData: res.data });
        });
    }

    handleChangePage(event, newPage) {
        const additionalParam = this.props.additionalParam ? this.props.additionalParam : '';
        this.setState({ page: newPage });
        axios.get(this.props.apiName + '?page=' + newPage + '&size=10&sort=id,desc' + additionalParam).then(res => {
            this.setState({ totalCount: parseInt(res.headers['x-total-count']) });
            this.setState({ dynamicData: res.data });
        });
    };

    handleChangeRowsPerPage(event) {
        console.log('handle row', event.target.value);
        // TODO we have to handle this in realtime integration.
    };

    handleClose() {
        this.setState({
            enableDialog: false
        })
    }

    handleSuccess() {
        axios.delete(this.props.apiName + '/' + this.state.deleteId).then(res => {
            this.loadData();
            this.setState({
                enableDialog: false
            })
        });
    }

    deleteOperation(id) {
        this.setState({
            enableDialog: true,
            deleteId: id
        });
    }

    render() {
        return (
            <div>
                <Paper elevation={0}>
                    <GridToolBar title={this.props.title} addRouter={this.props.addRouter} />
                    <TableContainer>
                        <Table size="small" aria-label="a dense table">
                            <TableHead>
                                <TableRow>
                                    {this.props.columns.map((column) => column.type !== 'currency' ? (
                                        < TableCell key={column.id}> {column.label}</TableCell>
                                    ) : < TableCell key={column.id} align="right"> {column.label}</TableCell>)}
                                    <TableCell>Action</TableCell>
                                </TableRow>
                            </TableHead>
                            <DynamicBody dynamicData={this.state.dynamicData} columns={this.props.columns}
                                addRouter={this.props.addRouter} deleteOperation={this.deleteOperation} apiName={this.props.apiName} />
                        </Table>
                    </TableContainer>
                    <TablePagination
                        rowsPerPageOptions={[5, 10, 25]}
                        component="div"
                        count={this.state.totalCount}
                        rowsPerPage={this.state.rowsPerPage}
                        page={this.state.page}
                        onChangePage={this.handleChangePage}
                        onChangeRowsPerPage={this.handleChangeRowsPerPage}
                    />
                </Paper>
                <DeleteDialog enableDialog={this.state.enableDialog} handleClose={this.handleClose} handleSuccess={this.handleSuccess} />
            </div>
        );
    }
}

function GridToolBar(props) {
    const useToolbarStyles = makeStyles((theme) => ({
        root: {
            paddingLeft: theme.spacing(1),
            paddingRight: theme.spacing(1),
        },
        highlight:
            theme.palette.type === 'light'
                ? {
                    color: theme.palette.secondary.main,
                    backgroundColor: lighten(theme.palette.secondary.light, 0.85),
                }
                : {
                    color: theme.palette.text.primary,
                    backgroundColor: theme.palette.secondary.dark,
                },
        title: {
            flex: '1 1 100%',
        },
    }));
    const classes = useToolbarStyles();
    return (
        <Toolbar className={classes.root}>
            <Typography className={classes.title} variant="h5" id="tableTitle" component="div">
                {props.title}
            </Typography>

            <Tooltip title="Filter">
                <IconButton aria-label="filter list">
                    <FilterListIcon />
                </IconButton>
            </Tooltip>
            <Tooltip title="Export">
                <IconButton aria-label="Export">
                    <ImportExportIcon />
                </IconButton>
            </Tooltip>
            <Link to={props.addRouter}>
                <Tooltip title="Create">
                    <IconButton aria-label="Create">
                        <AddIcon />
                    </IconButton>
                </Tooltip>
            </Link>
        </Toolbar>
    )
}

function DeleteDialog(props) {
    return (
        <Dialog
            open={props.enableDialog}
            onClose={props.handleClose}
            aria-labelledby="alert-dialog-title"
            aria-describedby="alert-dialog-description"
        >
            <DialogTitle id="alert-dialog-title">{"Delete!"}</DialogTitle>
            <DialogContent>
                <DialogContentText id="alert-dialog-description">
                    Are you sure you want to delete this!
          </DialogContentText>
            </DialogContent>
            <DialogActions>
                <Button onClick={props.handleClose} color="primary"> No </Button>
                <Button onClick={props.handleSuccess} color="primary" autoFocus> Yes</Button>
            </DialogActions>
        </Dialog>
    )
}

export default DataGrid;
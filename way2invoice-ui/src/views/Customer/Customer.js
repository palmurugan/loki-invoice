import React from 'react';

import { withRouter } from 'react-router-dom';
import { BreadCrumb } from '../../components';
import Accounts from '../Accounts'

const breadCrumbData = [
    { "label": "Account" },
    { "label": "Create" }
]

const Customer = () => {
    return (
        <div style={{ padding: 2 }}>
            <div style={{ marginLeft: 5, marginRight: 5 }}>
                <BreadCrumb data={breadCrumbData} />
                <Accounts />
            </div>
        </div>
    );
}
export default withRouter(Customer);
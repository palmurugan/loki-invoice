import React from 'react';

const withGridHolder = (WrappedComponent) => {
    return props => (
        <React.Fragment>
            <WrappedComponent />
        </React.Fragment>
    )
}

export default withGridHolder;
export const customerService = {
    prepareRequest, resolveResponse
}

function prepareRequest(request) {
    request = {
        ...request,
        id: request.id ? request.id : '',
        status: 'ACTIVE',
        company: request.company,
        customerCategoryId: request.category,
        billingPeriodId: request.billingCycle,
        billingTypeId: request.billingType,
        currencyId: request.currency,
        customerBillingInfo: {
            id: request.customerBillingInfoId ? request.customerBillingInfoId : '',
            billCreditLimit: request.billCreditLimit,
            creditLimit: request.creditLimit,
            status: 'ACTIVE',
        }, customerContacts: [{
            id: request.customerContactId ? request.customerContactId : '',
            address1: request.address1,
            city: request.city,
            country: request.country,
            state: request.state,
            zipcode: request.zipcode,
            address2: request.address2 ? request.address2 : '',
            status: 'ACTIVE',
        }]
    }
    return request;
}

function resolveResponse(response) {
    response = {
        id: response.id,
        customerBillingInfoId: response.customerBillingInfo.id,
        customerContactId: response.customerContacts[0].id,
        name: response.name,
        category: response.customerCategoryId,
        company: response.company,
        billingType: response.billingTypeId,
        billingCycle: response.billingPeriodId,
        currency: response.currencyId,
        address1: response.customerContacts[0].address1,
        city: response.customerContacts[0].city,
        country: response.customerContacts[0].country,
        state: response.customerContacts[0].state,
        zipcode: response.customerContacts[0].zipcode,
        address2: response.customerContacts[0].address2 ? response.customerContacts[0].address2 : '',
        billCreditLimit: response.customerBillingInfo.billCreditLimit,
        creditLimit: response.customerBillingInfo.creditLimit
    }
    return response;
}
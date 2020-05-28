export class Customer
{
    custId:number;
    custName:string;
    custMobile:string;
    custPassword:string;

    constructor(custId:number,custName:string,
        custMobile:string, custPassword:string)
        {
            this.custId = custId;
            this.custName = custName;
            this.custMobile = custMobile;
            this.custPassword = custPassword;
        }
}
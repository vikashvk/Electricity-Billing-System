export class Customer
{
    custId:number;
    custName:string;
    custMobile:string;
    custPassword:string;
    custAddress:string;
    custCity:string;

    constructor(custId:number,custName:string,
        custMobile:string, custPassword:string, custAddress:string , custCity:string)
        {
            this.custId = custId;
            this.custName = custName;
            this.custMobile = custMobile;
            this.custPassword = custPassword;
            this.custAddress = custAddress;
            this.custCity = custCity;
        }
}
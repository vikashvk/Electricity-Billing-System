export class Payment
{
    paymentId:number;
    transactionId:number;
    custId:number;
    amount:string;
    status:string

    constructor(paymentId:number,transactionId:number,
        custId:number, amount:String ,status:string)
        {
            this.paymentId =paymentId
            this.transactionId= transactionId;
            this.custId = custId;
          
            this.status = status;
           
        }
}
export interface Payment {
    id: number;
    transactionId: string;
    custId: number;
    amount: number;
    status: boolean;
    date:string;

}
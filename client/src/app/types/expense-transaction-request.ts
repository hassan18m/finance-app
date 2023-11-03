export class ExpenseTransactionRequest {
    constructor(
        public amount: number,
        public description: string,
        public categoryName: string,
        public recipient: string,
        public paymentMethod: string,
        public location: string
    ) { }
}
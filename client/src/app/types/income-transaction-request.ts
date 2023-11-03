export class IncomeTransactionRequest {
    constructor(
        public amount: number,
        public description: string,
        public paymentMethod: string,
        public location: string
    ) { }
}
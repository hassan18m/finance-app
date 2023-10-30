import { TransactionExpenseCategory } from "./transaction-expense-category";

export class Transaction {
    constructor(public id: number,
        public amount: number,
        public transactionDateTime: Date,
        public transactionType: string,
        public description: string,
        public expenseCategory: TransactionExpenseCategory,
        public recipient: string,
        public paymentMethod: string,
        public location: string) { }
}
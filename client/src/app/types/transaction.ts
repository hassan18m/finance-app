import { ExpenseCategory } from "./expense-category";

export class Transaction {
    constructor(public id: number,
        public amount: number,
        public transactionDateTime: Date,
        public transactionType: string,
        public description: string,
        public expenseCategory: ExpenseCategory,
        public recipient: string,
        public paymentMethod: string,
        public location: string) { }
}
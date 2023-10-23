import { ExpenseCategory } from "./expense-category"

export class Budget {
    constructor(private id: number,
        private amount: number,
        private startDate: Date,
        private endDate: Date,
        expenseCategories: ExpenseCategory[]) { }
}
import { BudgetExpenseCategory } from "./budget-expense-category"

export class Budget {
    constructor(public id: number,
        public amount: number,
        public startDate: Date,
        public endDate: Date,
        public expenseCategories: BudgetExpenseCategory[]) { }
}
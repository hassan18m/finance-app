import { Budget } from "./budget";

export class TransactionExpenseCategory {
    constructor(public categoryName: string,
        public budgets: Budget[]) {}
    
}
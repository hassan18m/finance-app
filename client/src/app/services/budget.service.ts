import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { StorageService } from './storage.service';
import { Budget } from '../types/budget';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BudgetService {
  budgetURL: string = 'http://localhost:8080/api/v1/budgets/';

  constructor(private http: HttpClient,
    private storageService: StorageService) { }

  getUserBudgets(): Observable<Budget[]> {
    const userBudgetURL = this.budgetURL + this.storageService.getUser().id;
    return this.http.get<Budget[]>(userBudgetURL, { withCredentials: true });
  }

  addBudgetToUser(budgetRequest: any) {
    const addBudgetURL = `http://localhost:8080/api/v1/users/${this.storageService.getUser().id}/add-budget`;
    return this.http.post(addBudgetURL, budgetRequest, { withCredentials: true });
  }

  removeBudget(budgetId: number) {
    const removeBudgetURL = this.budgetURL + budgetId;
    return this.http.delete(removeBudgetURL, { withCredentials: true });
  }

  updateBudget(budgetId: number, updateBudgetReq: any) {
    const updateBudgetURL = this.budgetURL + budgetId + '/update';
    return this.http.patch(updateBudgetURL, updateBudgetReq, { withCredentials: true });
  }
}

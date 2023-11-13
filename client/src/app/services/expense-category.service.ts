import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ExpenseCategoryService {
  expenseCategoriesURL: string = 'http://localhost:8080/api/v1/expense-categories';

  constructor(private http: HttpClient) { }

  getAllExpenseCategories(): Observable<string[]> {
    return this.http.get<string[]>(this.expenseCategoriesURL, { withCredentials: true });
  }
}

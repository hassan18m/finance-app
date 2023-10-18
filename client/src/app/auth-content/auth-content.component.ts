import { Component } from '@angular/core';
import { AxiosService } from '../services/axios.service';

@Component({
  selector: 'app-auth-content',
  templateUrl: './auth-content.component.html',
  styleUrls: ['./auth-content.component.css']
})
export class AuthContentComponent {
  firstName: string = "";
  lastName: string = "";
  data: any = [
    this.firstName,
    this.lastName
  ];

  constructor(private axiosService: AxiosService) {  }

  ngOnInit(): void {
    this.axiosService.request(
      "GET",
      "/users",
      (this.data)
    ).then(
      (response) => this.data = response.data
    );
  }
}

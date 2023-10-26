// import { Component, OnInit } from '@angular/core';
// import { UserService } from 'src/app/services/user.service';
// import { User, UserData } from 'src/app/types/user';
// import { MatTableDataSource } from '@angular/material/table';

// @Component({
//   selector: 'app-userlist',
//   templateUrl: './userlist.component.html',
//   styleUrls: ['./userlist.component.css']
// })
// export class UserlistComponent implements OnInit {

//   users!: User[];
//   usersData!: UserData[];
//   displayedColumns: string[] = ['id', 'firstName', 'lastName', 'email'];
//   ELEMENT_DATA: UserData[] = this.users;
//   dataSource: MatTableDataSource<UserData> = new MatTableDataSource();

//   constructor(private userSerivce: UserService) { }

//   ngOnInit(): void {
//     this.userSerivce.getUsers().subscribe((data) => {
//       this.users = data
//       const dataSourceUsers = data.map((user: User) => {
//         return {
//           id: user.id,
//           firstName: user.firstName,
//           lastName: user.lastName,
//           email: user.email,
//         } as UserData;
//       });
//       this.usersData = dataSourceUsers;
//       this.dataSource = new MatTableDataSource(dataSourceUsers);
//     });
//   }
// }


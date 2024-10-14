// import { Component, OnInit } from '@angular/core';
// import { AuthenticateService } from 'src/app/services/authenticate-service.service';

// @Component({
//   selector: 'app-dashbord',
//   templateUrl: './dashbord.component.html',
//   styleUrls: ['./dashbord.component.scss']
// })
// export class DashbordComponent implements OnInit {


//   isSidebarActive = false;

//   toggleSidebar() {
//     this.isSidebarActive = !this.isSidebarActive;
//   }

//   constructor(
//     private service: AuthenticateService,
//   ){}

//   onLogout(): void {
//     this.service.logout();
//   }

  

//   ngOnInit(): void {
    
//   }


// }
import { Component, OnInit } from '@angular/core';
import { AuthenticateService } from 'src/app/services/authenticate-service.service';

@Component({
  selector: 'app-dashbord',
  templateUrl: './dashbord.component.html',
  styleUrls: ['./dashbord.component.scss']
})
export class DashbordComponent implements OnInit {
  isSidebarActive = false;

  constructor(private service: AuthenticateService) {}

  ngOnInit(): void {
    // Initialize any necessary data
  }

  toggleSidebar() {
    this.isSidebarActive = !this.isSidebarActive;
  }

  onLogout(): void {
    this.service.logout();
  }
}
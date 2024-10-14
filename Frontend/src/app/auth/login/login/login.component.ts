// import { Component, inject, OnInit } from '@angular/core';
// import { FormBuilder, FormGroup, Validators } from '@angular/forms';
// import { Router } from '@angular/router';
// import { JwtHelperService } from '@auth0/angular-jwt';
// import { Jwt } from 'src/app/interfaces/Jwt';
// import { AuthenticateService } from 'src/app/services/authenticate-service.service';

// @Component({
//   selector: 'app-login',
//   templateUrl: './login.component.html',
//   styleUrls: ['./login.component.scss']
// })
// export class LoginComponent implements OnInit{


//   loginForm!: FormGroup;
 
  
//   ngOnInit(): void {
//     this.loginForm = this.fb.group({
//       email: ['', [Validators.required, Validators.email]],
//       password: ['', [Validators.required]],
//     })
//   }

//   constructor(
//     private service: AuthenticateService,
//     private fb: FormBuilder,
//     private router: Router,
//     private jwtHelper: JwtHelperService 
//   ) {}
  
//   submitForm(): void {
//     console.log(this.loginForm.value);
//     this.service.login(this.loginForm.value).subscribe(
//       (response: Jwt) => {
//         const jwToken = response.token;
//         const userRole = response.role; 
//         console.log("The role:", userRole);
  
//         localStorage.setItem('jwt', jwToken);
//         localStorage.setItem('role', userRole);
  
//         if (userRole === 'ADMIN') {
//           this.router.navigateByUrl('/dashboard');
//         } else if (userRole === 'USER') {
//           this.router.navigateByUrl('home');
//         } else if (userRole === 'FOURNISSEUR') {
//           this.router.navigateByUrl('/dashboard/fournisseur-dashboard'); 
//         } else {
//           this.router.navigateByUrl('/dashboard'); 
//         }
//       }
//     );
//   }
  
    
// }
// import { Component, OnInit } from '@angular/core';
// import { FormBuilder, FormGroup, Validators } from '@angular/forms';
// import { Router } from '@angular/router';
// import { JwtHelperService } from '@auth0/angular-jwt';
// import { Jwt } from 'src/app/interfaces/Jwt';
// import { AuthenticateService } from 'src/app/services/authenticate-service.service';

// @Component({
//   selector: 'app-login',
//   templateUrl: './login.component.html',
//   styleUrls: ['./login.component.scss']
// })
// export class LoginComponent implements OnInit {
//   loginForm!: FormGroup;
//   loginError: string = '';
//   emailError: string = '';
//   passwordError: string = '';

//   constructor(
//     private service: AuthenticateService,
//     private fb: FormBuilder,
//     private router: Router,
//     private jwtHelper: JwtHelperService
//   ) {}

//   ngOnInit(): void {
//     this.service.logout()
//     this.loginForm = this.fb.group({
//       email: ['', [Validators.required, Validators.email]],
//       password: ['', [Validators.required]],
//     });
//   }

//   submitForm(): void {
//     if (this.loginForm.valid) {
//       this.clearErrors();
//       console.log(this.loginForm.value);
//       this.service.login(this.loginForm.value).subscribe(
//         (response: Jwt) => {
//           const jwToken = response.token;
//           const userRole = response.role;

//           console.log("The role:", userRole);
    
//           localStorage.setItem('jwt', jwToken);
//           localStorage.setItem('role', userRole);
    
//           if (userRole === 'ADMIN') {
//             this.router.navigateByUrl('/dashboard');
//           } else if (userRole === 'USER') {
//             this.router.navigateByUrl('home');
//           } else if (userRole === 'FOURNISSEUR') {
//             this.router.navigateByUrl('/dashboard/fournisseur-dashboard');
//           } else {
//             this.router.navigateByUrl('/dashboard');
//           }
//         },
//         (error) => {
//           console.error('Login failed:', error);
//           if (error.status === 401) {
//             if (error.error && error.error.field) {
//               switch (error.error.field) {
//                 case 'email':
//                   this.emailError = 'The email address is not registered.';
//                   break;
//                 case 'password':
//                   this.passwordError = 'The password is incorrect.';
//                   break;
//                 default:
//                   this.loginError = 'Incorrect email or password. Please try again.';
//               }
//             } else {
//               this.loginError = 'Incorrect email or password. Please try again.';
//             }
//           } else {
//             this.loginError = 'An error occurred. Please try again later.';
//           }
//         }
//       );
//     } else {
//       this.markFormGroupTouched(this.loginForm);
//     }
//   }

//   private clearErrors(): void {
//     this.loginError = '';
//     this.emailError = '';
//     this.passwordError = '';
//   }

//   private markFormGroupTouched(formGroup: FormGroup) {
//     Object.values(formGroup.controls).forEach(control => {
//       control.markAsTouched();

//       if (control instanceof FormGroup) {
//         this.markFormGroupTouched(control);
//       }
//     });
//   }
// }

import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Jwt } from 'src/app/interfaces/Jwt';
import { AuthenticateService } from 'src/app/services/authenticate-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  loginError: string = '';
  emailError: string = '';
  passwordError: string = '';

  constructor(
    private service: AuthenticateService,
    private fb: FormBuilder,
    private router: Router,
    private jwtHelper: JwtHelperService
  ) {}

  ngOnInit(): void {
    this.service.logout();
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
      remember: [false]
    });
  }

  submitForm(): void {
    if (this.loginForm.valid) {
      this.clearErrors();
      console.log(this.loginForm.value);
      this.service.login(this.loginForm.value).subscribe(
        (response: Jwt) => {
          const jwToken = response.token;
          const userRole = response.role;

          console.log("The role:", userRole);
    
          localStorage.setItem('jwt', jwToken);
          localStorage.setItem('role', userRole);
    
          if (userRole === 'ADMIN') {
            this.router.navigateByUrl('/dashboard');
          } else if (userRole === 'USER') {
            this.router.navigateByUrl('home');
          } else if (userRole === 'FOURNISSEUR') {
            this.router.navigateByUrl('/dashboard/fournisseur-dashboard');
          } else {
            this.router.navigateByUrl('/dashboard');
          }
        },
        (error) => {
          console.error('Login failed:', error);
          if (error.status === 401) {
            if (error.error && error.error.field) {
              switch (error.error.field) {
                case 'email':
                  this.emailError = 'The email address is not registered.';
                  break;
                case 'password':
                  this.passwordError = 'The password is incorrect.';
                  break;
                default:
                  this.loginError = 'Incorrect email or password. Please try again.';
              }
            } else {
              this.loginError = 'Incorrect email or password. Please try again.';
            }
          } else {
            this.loginError = 'An error occurred. Please try again later.';
          }
        }
      );
    } else {
      this.markFormGroupTouched(this.loginForm);
    }
  }

  private clearErrors(): void {
    this.loginError = '';
    this.emailError = '';
    this.passwordError = '';
  }

  private markFormGroupTouched(formGroup: FormGroup) {
    Object.values(formGroup.controls).forEach(control => {
      control.markAsTouched();

      if (control instanceof FormGroup) {
        this.markFormGroupTouched(control);
      }
    });
  }
}
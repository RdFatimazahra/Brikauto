import { Component, inject, OnInit } from '@angular/core';
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
export class LoginComponent implements OnInit{


  loginForm!: FormGroup;
 
  
  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
    })
  }

  constructor(
    private service: AuthenticateService,
    private fb: FormBuilder,
    private router: Router,
    private jwtHelper: JwtHelperService 
  ) {}
  
  submitForm(): void {
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
      }
    );
  }
  
    
}


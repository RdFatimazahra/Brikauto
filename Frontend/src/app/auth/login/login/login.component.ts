/* import { Component, inject, OnInit } from '@angular/core';
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
    private jwtHelper: JwtHelperService // Inject the service
  ) {}
  
  submitForm(): void {
    console.log(this.loginForm.value);
    this.service.login(this.loginForm.value).subscribe(
      (response: Jwt) => {
        const jwToken = response.token;
        const userRole = response.role; // Directly use the role from the response
        console.log("The role:", userRole);
  
        localStorage.setItem('jwt', jwToken);
        localStorage.setItem('role', userRole);
  
        if (userRole === 'ADMIN') {
          this.router.navigateByUrl('/dashboard');
        } else if (userRole === 'USER') {
          this.router.navigateByUrl('/dashboard/user-dashboard');
        } else if (userRole === 'FOURNISSEUR') {
          this.router.navigateByUrl('/dashboard/fournisseur-dashboard'); // Adjusted path
        } else {
          this.router.navigateByUrl('/dashboard'); // Default route
        }
      }
    );
  }
  
    
}
 */
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
  registerForm!: FormGroup;
  
  constructor(
    private service: AuthenticateService,
    private fb: FormBuilder,
    private router: Router,
    private jwtHelper: JwtHelperService
  ) {}
  
  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
    });

    this.registerForm = this.fb.group({
      nom: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
    });

    this.setupFormSwitching();
  }
  
  submitForm(): void {
    console.log(this.loginForm.value);
    this.service.login(this.loginForm.value).subscribe(
      (response: Jwt) => {
        const jwToken = response.token;
        const userRole = response.role;
        console.log("The role:", userRole);
  
        localStorage.setItem('jwt', jwToken);
        localStorage.setItem('role', userRole);
  
        switch(userRole) {
          case 'ADMIN':
            this.router.navigateByUrl('/dashboard');
            break;
          case 'USER':
            this.router.navigateByUrl('/show');
            break;
          // case 'FOURNISSEUR':
          //   this.router.navigateByUrl('/dashboard');
          //   break;
          default:
            this.router.navigateByUrl('/dashboard');
        }
      }
    );
  }

  submitRegisterForm(): void {
    if (this.registerForm.valid) {
      this.service.register(this.registerForm.value).subscribe(
        (response: Jwt) => {
          console.log('Registration successful', response);
          // Optionally, you can automatically log the user in after registration
          this.registerForm.patchValue({
            nom : this.registerForm.get('nom')?.value,
            email: this.registerForm.get('email')?.value,
            password: this.registerForm.get('password')?.value
          });
          this.submitForm(); // This will log the user in
        },
        error => {
          console.error('Registration failed', error);
          // Handle registration error (show message to user, etc.)
        }
      );
    }
  }

  private setupFormSwitching(): void {
    const loginForm = document.getElementById('login-form');
    const registerForm = document.getElementById('register-form');
    const switchToRegister = document.getElementById('switch-to-register');
    const switchToLogin = document.getElementById('switch-to-login');
    const imageLeft = document.getElementById('image-left');
    const imageRight = document.getElementById('image-right');

    if (switchToRegister && switchToLogin && loginForm && registerForm && imageLeft && imageRight) {
      switchToRegister.addEventListener('click', (e) => {
        e.preventDefault();
        this.switchForms(false);
      });

      switchToLogin.addEventListener('click', (e) => {
        e.preventDefault();
        this.switchForms(true);
      });
    }
  }

  private switchForms(showLogin: boolean): void {
    const loginForm = document.getElementById('login-form');
    const registerForm = document.getElementById('register-form');
    const imageLeft = document.getElementById('image-left');
    const imageRight = document.getElementById('image-right');

    if (loginForm && registerForm && imageLeft && imageRight) {
      if (showLogin) {
        loginForm.classList.add('active');
        registerForm.classList.remove('active');
        imageLeft.style.display = 'flex';
        imageRight.style.display = 'none';
      } else {
        registerForm.classList.add('active');
        loginForm.classList.remove('active');
        imageLeft.style.display = 'none';
        imageRight.style.display = 'flex';
      }
    }
  }
}
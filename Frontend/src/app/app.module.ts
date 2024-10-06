import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';
import { JwtModule, JwtHelperService, JWT_OPTIONS } from '@auth0/angular-jwt';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './auth/login/login/login.component';
import { AuthInterceptor } from './core/auth.interceptor';
import { RouterModule } from '@angular/router';
import { DashbordComponent } from './shared/dashbord/dashbord/dashbord.component';
import { PieceListComponent } from './pages/piece/list-piece/list-piece/list-piece.component';
import { AddPieceComponent } from './pages/piece/add-piece/add-piece/add-piece.component';
import { EditPieceComponent } from './pages/piece/edit-piece/edit-piece/edit-piece.component';
import { AuthenticateService } from './services/authenticate-service.service';
import { AuthInterceptorInterceptor } from './interceptor/auth-interceptor.interceptor';
import { HomepageComponent } from './home/homepage/homepage.component';
import { NavbarComponent } from './shared/navbar/navbar.component';
import { OrderComponent } from './pages/order/order/order.component';
import { ShowPieceComponent } from './client/Show-piece/show-piece/show-piece.component';

// Function to retrieve the token
export function tokenGetter() {
  return localStorage.getItem('access_token');
}

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashbordComponent,
    PieceListComponent,
    AddPieceComponent,
    EditPieceComponent,
    HomepageComponent,
    NavbarComponent,
    OrderComponent,
    ShowPieceComponent
  ],
  imports: [
    BrowserModule,
    RouterModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    MatDialogModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: tokenGetter,
        allowedDomains: ['your-api-domain.com'], // Replace with your API domain
        disallowedRoutes: ['http://example.com/auth/'], // Replace with routes you want to skip
      },
    }),
  ],
  providers: [
    { provide: JWT_OPTIONS, useValue: JWT_OPTIONS },
    JwtHelperService,
    [AuthenticateService,
      { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptorInterceptor, multi: true }
    ],
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
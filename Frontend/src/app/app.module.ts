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
import { ShowPieceComponent } from './client/Show-piece/show-piece/show-piece.component';
import { PieceDetailsComponent } from './client/Show-piece/piece-details/piece-details.component';
import { CartComponent } from './client/cart/cart.component';
import { HeroSliderComponent } from './home/hero-slider/hero-slider.component';
import { RegisterComponent } from './auth/register/register.component';


import { CommonModule } from '@angular/common';
import { OrderConfirmationComponent } from './pages/order/order-confirmation/order-confirmation.component';
import { ShowOrdersComponent } from './pages/order/show-orders/show-orders.component';

import { NgxPaginationModule } from 'ngx-pagination';
import { HowItWorksComponent } from './home/how-it-works/how-it-works.component';
import { TopProductsComponent } from './home/top-products/top-products.component';
import { FooterComponent } from './home/footer/footer.component';
import { SliderComponent } from './home/slider/slider.component';

import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';

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
    ShowPieceComponent,
    PieceDetailsComponent,
    CartComponent,
    HeroSliderComponent,
    RegisterComponent,
    OrderConfirmationComponent,
    ShowOrdersComponent,
    HowItWorksComponent,
    TopProductsComponent,
    FooterComponent,
    SliderComponent

  ],
  imports: [
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    NgxPaginationModule,
    BrowserModule,
    RouterModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    MatDialogModule,
    CommonModule,
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
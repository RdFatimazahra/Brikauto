// import { NgModule } from '@angular/core';
// import { RouterModule, Routes } from '@angular/router';
// import { LoginComponent } from './auth/login/login/login.component';
// import { DashbordComponent } from './shared/dashbord/dashbord/dashbord.component';
// import { PieceListComponent } from './pages/piece/list-piece/list-piece/list-piece.component';
// import { AddPieceComponent } from './pages/piece/add-piece/add-piece/add-piece.component';
// import { HomepageComponent } from './home/homepage/homepage.component';
// import { NavbarComponent } from './shared/navbar/navbar.component';
// import { ShowPieceComponent } from './client/Show-piece/show-piece/show-piece.component';
// import { PieceDetailsComponent } from './client/Show-piece/piece-details/piece-details.component';
// import { CartComponent } from './client/cart/cart.component';
// import { RegisterComponent } from './auth/register/register.component';
// import { OrderConfirmationComponent } from './pages/order/order-confirmation/order-confirmation.component';
// import { ShowOrdersComponent } from './pages/order/show-orders/show-orders.component';

// const routes: Routes = [
//     { path: 'dashboard', component: DashbordComponent, children: [
        
//         // Ajoutez d'autres routes enfants ici
//     ] },
//     { path: 'list-piece', component: PieceListComponent },
//     { path: 'login', component: LoginComponent },
//     { path: 'register', component: RegisterComponent},
//     { path: '', redirectTo: '/home', pathMatch: 'full' },
//     {path: 'add-piece', component: AddPieceComponent},
//     {path: 'home', component: HomepageComponent},
//     {path: 'navbar', component: NavbarComponent},
//     {path: 'show', component: ShowPieceComponent},
//     {path:'piece-details/:id', component: PieceDetailsComponent},
//     {path: 'cart' , component: CartComponent},
//     // {path:'orderConfirmation/:id',component:OrderConfirmationComponent}
//     {path:'orderConfirme/:id', component:OrderConfirmationComponent},
//     {path: 'showOrders', component: ShowOrdersComponent}
// ];

// @NgModule({
//   imports: [RouterModule.forRoot(routes)],
//   exports: [RouterModule]
// })
// export class AppRoutingModule { }

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login/login.component';
import { PieceListComponent } from './pages/piece/list-piece/list-piece/list-piece.component';
import { AddPieceComponent } from './pages/piece/add-piece/add-piece/add-piece.component';
import { HomepageComponent } from './home/homepage/homepage.component';
import { NavbarComponent } from './shared/navbar/navbar.component';
import { ShowPieceComponent } from './client/Show-piece/show-piece/show-piece.component';
import { PieceDetailsComponent } from './client/Show-piece/piece-details/piece-details.component';
import { CartComponent } from './client/cart/cart.component';
import { RegisterComponent } from './auth/register/register.component';
import { OrderConfirmationComponent } from './pages/order/order-confirmation/order-confirmation.component';
import { ShowOrdersComponent } from './pages/order/show-orders/show-orders.component';
import { DashbordComponent } from './shared/dashbord/dashbord/dashbord.component';
import { HowItWorksComponent } from './home/how-it-works/how-it-works.component';
import { TopProductsComponent } from './home/top-products/top-products.component';
import { FooterComponent } from './home/footer/footer.component';
import { SliderComponent } from './home/slider/slider.component';

const routes: Routes = [
  { 
    path: 'dashboard', 
    component: DashbordComponent, 
    children: [
      { path: 'list-piece', component: PieceListComponent },
      { path: 'add-piece', component: AddPieceComponent },
      { path: 'showOrders', component: ShowOrdersComponent },
      { path: '', redirectTo: 'list-piece', pathMatch: 'full' }
    ] 
  },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent},
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomepageComponent},
  { path: 'navbar', component: NavbarComponent},
  { path: 'show', component: ShowPieceComponent},
  { path: 'piece-details/:id', component: PieceDetailsComponent},
  { path: 'cart' , component: CartComponent},
  { path: 'orderConfirme/:id', component: OrderConfirmationComponent},
  { path: 'how' , component: HowItWorksComponent},
  {path: 'top' , component: TopProductsComponent},
  {path: 'footer', component: FooterComponent},
  {path: 'slider', component: SliderComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
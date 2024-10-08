import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login/login.component';
import { DashbordComponent } from './shared/dashbord/dashbord/dashbord.component';
import { PieceListComponent } from './pages/piece/list-piece/list-piece/list-piece.component';
import { AddPieceComponent } from './pages/piece/add-piece/add-piece/add-piece.component';
import { HomepageComponent } from './home/homepage/homepage.component';
import { NavbarComponent } from './shared/navbar/navbar.component';
import { ShowPieceComponent } from './client/Show-piece/show-piece/show-piece.component';
import { PieceDetailsComponent } from './client/Show-piece/piece-details/piece-details.component';
import { CartComponent } from './client/cart/cart.component';
import { RegisterComponent } from './auth/register/register.component';

const routes: Routes = [
    { path: 'dashboard', component: DashbordComponent, children: [
        
        // Ajoutez d'autres routes enfants ici
    ] },
    { path: 'list-piece', component: PieceListComponent },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent},
    { path: '', redirectTo: '/home', pathMatch: 'full' },
    {path: 'add-piece', component: AddPieceComponent},
    {path: 'home', component: HomepageComponent},
    {path: 'navbar', component: NavbarComponent},
    {path: 'show', component: ShowPieceComponent},
    {path:'piece-details/:id', component: PieceDetailsComponent},
    {path: 'cart' , component: CartComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

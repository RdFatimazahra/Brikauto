import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login/login.component';
import { DashbordComponent } from './shared/dashbord/dashbord/dashbord.component';
import { PieceListComponent } from './pages/piece/list-piece/list-piece/list-piece.component';
import { AddPieceComponent } from './pages/piece/add-piece/add-piece/add-piece.component';
import { HomepageComponent } from './home/homepage/homepage.component';
import { NavbarComponent } from './shared/navbar/navbar.component';
import { OrderComponent } from './pages/order/order/order.component';

const routes: Routes = [
    { path: 'dashboard', component: DashbordComponent, children: [
        
        // Ajoutez d'autres routes enfants ici
    ] },
    { path: 'list-piece', component: PieceListComponent },
    { path: 'login', component: LoginComponent },
    { path: '', redirectTo: '/login', pathMatch: 'full' },
    {path: 'add-piece', component: AddPieceComponent},
    {path: 'home', component: HomepageComponent},
    {path: 'navbar', component: NavbarComponent},
    {path: 'order', component: OrderComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

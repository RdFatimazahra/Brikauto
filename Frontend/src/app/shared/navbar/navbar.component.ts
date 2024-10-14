import { Component } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {
  loginLink = '/login';
  navItems = [
    { label: 'Home', link: '/' },
    { label: 'Categories', link: '/categories' },
    { label: 'Deals', link: '/deals' },
    { label: 'About', link: '/about' },
    { label: 'Contact', link: '/contact' }
  ];

  cartLink = '/cart';          
  // wishlistLink = '/wishlist';  
  cartItemCount = 0;          
  // wishlistItemCount = 0;      
  

}
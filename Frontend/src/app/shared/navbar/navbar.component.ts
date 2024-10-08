import { Component } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {
  navItems = [
    { label: 'Home', link: '/' },
    { label: 'Categories', link: '/categories' },
    { label: 'Deals', link: '/deals' },
    { label: 'About', link: '/about' },
    { label: 'Contact', link: '/contact' }
  ];

  cartLink = '/cart';          // Define the link for the cart page
  wishlistLink = '/wishlist';  // Define the link for the wishlist page
  cartItemCount = 0;          // You can dynamically update this count based on the user's cart
  wishlistItemCount = 0;      // You can dynamically update this count based on the user's wishlist
  
  // Optionally, you can load the cart and wishlist counts from a service or API
}
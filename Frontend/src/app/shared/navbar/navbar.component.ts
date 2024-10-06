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

}

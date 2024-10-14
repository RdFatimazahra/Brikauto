import { Component } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent {
  currentYear: number = new Date().getFullYear();

  footerLinks = [
    { title: 'Company', links: ['About Us', 'Careers', 'Press', 'Blog'] },
    { title: 'Products', links: ['Auto Parts', 'Accessories', 'Tools', 'Tires'] },
    { title: 'Support', links: ['Contact Us', 'FAQs', 'Shipping', 'Returns'] },
    { title: 'Legal', links: ['Terms of Service', 'Privacy Policy', 'Cookie Policy'] }
  ];

  socialLinks = [
    { name: 'Facebook', icon: 'facebook', url: 'https://facebook.com' },
    { name: 'Twitter', icon: 'twitter', url: 'https://twitter.com' },
    { name: 'Instagram', icon: 'instagram', url: 'https://instagram.com' },
    { name: 'LinkedIn', icon: 'linkedin', url: 'https://linkedin.com' }
  ];
}
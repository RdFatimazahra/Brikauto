import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.scss']
})
export class HomepageComponent {
  categories = [
    { name: 'Engine Parts', image: 'assets/engine.jpg' },
    { name: 'Brakes', image: 'assets/brakes.jpg' },
    { name: 'Suspension', image: 'assets/suspension.jpg' },
    { name: 'Electrical', image: 'assets/electricaal.jpg' },
  ];

  constructor(private router: Router) {}

  navigateToPartsList() {
    this.router.navigate(['/parts-list']);
  }

}

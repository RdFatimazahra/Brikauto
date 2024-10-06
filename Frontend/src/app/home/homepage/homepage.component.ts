import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.scss']
})
export class HomepageComponent {
  categories = [
    { name: 'Engine Parts', image: 'assets/engine.png' },
    { name: 'Brakes', image: 'assets/brakes.png' },
    { name: 'Suspension', image: 'assets/suspension.png' },
    { name: 'Electrical', image: 'assets/electrical.png' },
  ];

  constructor(private router: Router) {}

  navigateToPartsList() {
    this.router.navigate(['/parts-list']);
  }

}

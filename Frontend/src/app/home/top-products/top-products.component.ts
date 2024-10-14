import { Component } from '@angular/core';

interface Product {
  name: string;
  image: string;
  rating: number;
  price: number;
}

@Component({
  selector: 'app-top-products',
  templateUrl: './top-products.component.html',
  styleUrls: ['./top-products.component.scss']
})
export class TopProductsComponent {
  products: Product[] = [
    { name: '17 Inch Rim', image: 'assets/top-products-1.jpg', rating: 4.5, price: 89.00 },
    { name: 'Lubricants', image: 'assets/top-products-2.jpg', rating: 4, price: 99.00 },
    { name: 'Isphat Rim', image: 'assets/top-products-3.jpg', rating: 4, price: 55.00 },
    { name: 'Car Engine', image: 'assets/top-products-10.jpg', rating: 4.5, price: 39.00 },
    { name: 'Wheel', image: 'assets/top-products-8.jpg', rating: 4, price: 35.00 },
    { name: 'Metal-3d-Car Render', image: 'assets/top-products-7.jpg', rating: 4, price: 55.00 }
  ];

  getStars(rating: number): string[] {
    const fullStars = Math.floor(rating);
    const halfStar = rating % 1 >= 0.5;
    const emptyStars = 5 - fullStars - (halfStar ? 1 : 0);

    return [
      ...Array(fullStars).fill('★'),
      ...(halfStar ? ['½'] : []),
      ...Array(emptyStars).fill('☆')
    ];
  }
}
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss']
})
export class OrderComponent {
  orderForm: FormGroup;
  orderItems = [
    { name: 'Oil Filter', price: 12.99, quantity: 2 },
    { name: 'Brake Pads', price: 39.99, quantity: 1 },
    { name: 'Spark Plugs', price: 8.99, quantity: 4 }
  ];

  constructor(private fb: FormBuilder) {
    this.orderForm = this.fb.group({
      fullName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      address: ['', Validators.required],
      city: ['', Validators.required],
      zipCode: ['', Validators.required],
      cardNumber: ['', [Validators.required, Validators.pattern(/^\d{16}$/)]],
      expirationDate: ['', [Validators.required, Validators.pattern(/^(0[1-9]|1[0-2])\/\d{2}$/)]],
      cvv: ['', [Validators.required, Validators.pattern(/^\d{3}$/)]]
    });
  }

  get totalPrice(): number {
    return this.orderItems.reduce((total, item) => total + item.price * item.quantity, 0);
  }

  onSubmit() {
    if (this.orderForm.valid) {
      console.log('Order submitted', this.orderForm.value);
      // Here you would typically send the order to your backend
    } else {
      console.log('Form is invalid');
    }
  }

}

import { Component } from '@angular/core';
import { ProductService } from '../../service/product.service';
import { CommonModule } from '@angular/common';
import { Product } from '../../model/product.model';

@Component({
  selector: 'app-home',
  imports: [CommonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent {
  addToCart(_t3: any) {
    throw new Error('Method not implemented.');
  }
  buyNow(_t3: any) {
    throw new Error('Method not implemented.');
  }
  products: Product[] = [];

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.productService.getProducts().subscribe({
      next: (data: Product[]) => {
        this.products = data;
      },
      error: (error: any) => {
        console.error('Error fetching customers:', error);
      },
    });
  }
}

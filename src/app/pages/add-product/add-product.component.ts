import { Component } from '@angular/core';
import { ProductService } from '../../service/product.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-add-product',
  imports: [FormsModule, CommonModule],
  templateUrl: './add-product.component.html',
  styleUrl: './add-product.component.css',
})
export class AddProductComponent {
  product = {
    productName: '',
    productPrice: 0,
    productCategory: '',
    productDescription: '',
  };

  successMessage: string | null = null;
  errorMessage: string | null = null;

  constructor(private productService: ProductService) {}

  addProduct(): void {
    this.productService.addProduct(this.product).subscribe({
      next: () => {
        this.successMessage = 'Product added successfully!';
        this.errorMessage = null;
        this.resetForm();
      },
      error: (err: string) => {
        this.successMessage = null;
        this.errorMessage = 'Failed to add product. Please try again.';
        console.error(err);
      },
    });
  }

  resetForm(): void {
    this.product = {
      productName: '',
      productPrice: 0,
      productCategory: '',
      productDescription: '',
    };
  }
}

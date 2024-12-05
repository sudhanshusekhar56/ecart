import { Component } from '@angular/core';
import { ProductService } from '../../service/product.service';
import { Product } from '../../model/product.model';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-view-products',
  imports: [CommonModule, FormsModule],
  templateUrl: './view-products.component.html',
  styleUrls: ['./view-products.component.css'],
})
export class ViewProductsComponent {
  products: Product[] = [];
  isUpdateFormVisible = false;
  selectedProduct: Product = {
    productId: 0,
    productName: '',
    productPrice: 0,
    productCategory: '',
    productDescription: '',
    isAvailable: true,
  };

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    // Fetch all products on component load
    this.fetchProducts();
  }

  // Method to fetch all products
  fetchProducts(): void {
    this.productService.getProducts().subscribe({
      next: (data: Product[]) => {
        this.products = data;
      },
      error: (error: any) => {
        console.error('Error fetching products:', error);
      },
    });
  }

  // Show the Update form with the selected product data
  openUpdateForm(product: Product): void {
    this.selectedProduct = { ...product }; // Create a copy of the product
    this.isUpdateFormVisible = true;
  }

  // Hide the Update form
  closeUpdateForm(): void {
    this.isUpdateFormVisible = false;
  }

  // Delete product and refetch the product list
  deleteProduct(productId: number): void {
    this.productService.deleteProduct(productId).subscribe({
      next: () => {
        this.fetchProducts();
        alert('Product deleted successfully');
      },
      error: (error: any) => {
        console.error('Error deleting product:', error);
        alert('Error deleting product');
      },
    });
  }

  // Update the selected product
  updateProduct(): void {
    // this.productService.updateProduct(this.selectedProduct).subscribe({
    //   next: () => {
    //     // Update the product in the table
    //     const index = this.products.findIndex(
    //       (product) => product.productId === this.selectedProduct.productId
    //     );
    //     if (index !== -1) {
    //       this.products[index] = { ...this.selectedProduct }; // Update the product in the list
    //     }
    //     this.closeUpdateForm();
    //     alert('Product updated successfully');
    //   },
    //   error: (error: any) => {
    //     console.error('Error updating product:', error);
    //     alert('Error updating product');
    //   },
    // });
  }
}

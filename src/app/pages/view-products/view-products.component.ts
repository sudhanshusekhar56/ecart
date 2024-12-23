import { Component, ViewChild } from "@angular/core";
import { ProductService } from "../../service/product.service";
import { Product } from "../../model/product.model";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { PopupComponent } from "../../components/popup/popup.component";
import { GoBackComponent } from "../../components/go-back/go-back.component";

@Component({
  selector: "app-view-products",
  imports: [CommonModule, FormsModule, PopupComponent, GoBackComponent],
  templateUrl: "./view-products.component.html",
  styleUrls: ["./view-products.component.css"],
})
export class ViewProductsComponent {
  products: Product[] = [];
  isUpdateFormVisible = false;
  selectedProduct: Product = {
    productId: 0,
    productName: "",
    productPrice: 0,
    productCategory: "",
    productDescription: "",
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
        console.error("Error fetching products:", error);
      },
    });
  }

  @ViewChild("popup") popup!: PopupComponent;
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
        this.popup.showPopup("Product deleted successfully");
      },
      error: (error: any) => {
        console.error("Error deleting product:", error);
        this.popup.showPopup("Error deleting product");
      },
    });
  }

  updateProduct(): void {
    this.productService
      .updateProduct(this.selectedProduct, this.selectedProduct.productId)
      .subscribe({
        next: (updatedProduct: Product) => {
          this.fetchProducts();
          this.closeUpdateForm();
          this.popup.showPopup("Product updated successfully");
        },
        error: (error: any) => {
          console.error("Error updating product:", error);
          this.popup.showPopup("Error updating product");
        },
      });
  }
}

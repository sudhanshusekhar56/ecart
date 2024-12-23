import { CommonModule } from "@angular/common";
import {
  Component,
  EventEmitter,
  Input,
  Output,
  ViewChild,
} from "@angular/core";
import { FormsModule } from "@angular/forms";
import { PopupComponent } from "../popup/popup.component";

@Component({
  selector: "app-order-popup",
  imports: [FormsModule, CommonModule, PopupComponent],
  templateUrl: "./order-popup.component.html",
  styleUrl: "./order-popup.component.css",
})
export class OrderPopupComponent {
  @Input() isVisible: boolean = false;
  @Input() productDetails: any;
  @Output() onConfirmOrder = new EventEmitter<any>();
  @Output() onCancelOrder = new EventEmitter<void>();

  @ViewChild("popup") popup!: PopupComponent;

  address: string = "";
  phone: string = "";

  // Phone number validation method
  isValidPhoneNumber(phone: string): boolean {
    const phoneRegex = /^[0-9]{10}$/;
    return phoneRegex.test(phone);
  }

  onSubmit(): void {
    if (this.address && this.phone) {
      if (this.isValidPhoneNumber(this.phone)) {
        this.onConfirmOrder.emit({
          address: this.address,
          phone: this.phone,
        });
      } else {
        this.popup.showPopup("Please enter a valid phone number.");
      }
    } else {
      this.popup.showPopup("Please fill in all fields.");
    }
  }

  closePopup(): void {
    this.onCancelOrder.emit();
  }
}

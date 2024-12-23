import { CommonModule } from "@angular/common";
import { Component, Input } from "@angular/core";

@Component({
  selector: "app-popup",
  imports: [CommonModule],
  templateUrl: "./popup.component.html",
  styleUrl: "./popup.component.css",
})
export class PopupComponent {
  @Input() message: string = "";
  isVisible: boolean = false;

  showPopup(message: string) {
    this.message = message;
    this.isVisible = true;

    setTimeout(() => {
      this.isVisible = false;
    }, 10000);
  }

  closePopup() {
    this.isVisible = false;
  }
}

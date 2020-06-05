import { Component, OnInit, Inject } from '@angular/core';
import { CustomerService } from 'src/app/services/customer.service';
import {saveAs as importedSaveAs} from "file-saver";;
@Component({
  selector: 'app-view-bill-history',
  templateUrl: './view-bill-history.component.html',
  styleUrls: ['./view-bill-history.component.scss']
})
export class ViewBillHistoryComponent implements OnInit {
  bills: any[] = [];
  constructor(private customerService: CustomerService) { }
  ngOnInit(): void {
    this.getAllBills();
  }
  getAllBills() {
    this.customerService.getAllBills()
      .subscribe((data) => {
        console.log(data);
        this.bills = data
      }, error => {
        throw error;
      })
  }
  downloadBillPdf(billId: number) {
    this.customerService.downloadBillPdf(billId).subscribe(
      data => {
        importedSaveAs(data, "test.pdf");
      }, error => {
        console.log(error);
        alert('Bill with id ' + billId + ' could not be downloaded.');
      }

    );

  }
}

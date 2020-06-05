import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewPaymentHistoryComponent } from './view-payment-history.component';

describe('ViewPaymentHistoryComponent', () => {
  let component: ViewPaymentHistoryComponent;
  let fixture: ComponentFixture<ViewPaymentHistoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewPaymentHistoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewPaymentHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewBillHistoryComponent } from './view-bill-history.component';

describe('ViewBillHistoryComponent', () => {
  let component: ViewBillHistoryComponent;
  let fixture: ComponentFixture<ViewBillHistoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewBillHistoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewBillHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

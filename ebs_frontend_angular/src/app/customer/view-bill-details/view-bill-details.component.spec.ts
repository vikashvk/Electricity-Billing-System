import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewBillDetailsComponent } from './view-bill-details.component';

describe('ViewBillDetailsComponent', () => {
  let component: ViewBillDetailsComponent;
  let fixture: ComponentFixture<ViewBillDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewBillDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewBillDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddBillViewComponent } from './add-bill-view.component';

describe('AddBillViewComponent', () => {
  let component: AddBillViewComponent;
  let fixture: ComponentFixture<AddBillViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddBillViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddBillViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateBillViewComponent } from './update-bill-view.component';

describe('UpdateBillViewComponent', () => {
  let component: UpdateBillViewComponent;
  let fixture: ComponentFixture<UpdateBillViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateBillViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateBillViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

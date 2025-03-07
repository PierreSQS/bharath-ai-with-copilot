import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmcheckinComponent } from './confirmcheckin.component';

describe('ConfirmcheckinComponent', () => {
  let component: ConfirmcheckinComponent;
  let fixture: ComponentFixture<ConfirmcheckinComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ConfirmcheckinComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ConfirmcheckinComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

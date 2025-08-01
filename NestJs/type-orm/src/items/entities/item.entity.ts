import {
  Column,
  Entity,
  JoinColumn,
  JoinTable,
  ManyToMany,
  OneToMany,
  OneToOne,
} from 'typeorm';
import { Listing } from './listing.entity';
import { AbstractEntity } from '../../database/abstract-entity';
import { Comments } from './comments.entity';
import { Tag } from './tag.entity';

@Entity()
export class Item extends AbstractEntity<Item> {
  @Column()
  name: string;

  @Column({ default: true })
  public: boolean;

  // cascade -> will save listing to its table too
  @OneToOne(() => Listing, { cascade: true })
  @JoinColumn()
  listing: Listing;

  // target Entity, where
  @OneToMany(() => Comments, (comments) => comments.item, { cascade: true })
  comments: Comments[];

  @ManyToMany(() => Tag, { cascade: true })
  @JoinTable()
  tags: Tag[];
}

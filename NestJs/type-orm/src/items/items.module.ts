import { Module } from '@nestjs/common';
import { ItemsService } from './items.service';
import { ItemsController } from './items.controller';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Item } from './entities/item.entity';
import { Listing } from './entities/listing.entity';
import { ItemSubscriber } from './item.subscriber';
import { Tag } from './entities/tag.entity';
import { Comments } from './entities/comments.entity';

@Module({
  // for injecting forFeature to register which repos are registered in current scope [listing for saving]
  imports: [TypeOrmModule.forFeature([Item, Listing, Comments, Tag])],
  controllers: [ItemsController],
  providers: [ItemsService, ItemSubscriber],
})
export class ItemsModule {}

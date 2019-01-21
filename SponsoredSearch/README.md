# Sponsored Search Engine

Implemented:
- Scalable architecture and data model for the most of entities
- Auction business logic for RankByBid1 methodic (easily extendable to 3 other)
- Business logic for Bidders (managing of keywords, bids, etc.)
- Skeleton implementation of Slots (with basic functionality for Auction)
- RankBy... functionality is covered by the most interesting tests
- Implementation of a bidder is covered by the most interesting tests
- Basic tests for other parts for a demonstration of future tests structure

Highlights:
- Auction -> RankBy... (covered by AuctionTest)
- BaseBidder -> Bidder (covered by BidderTest)

Out of scope:
- SearchEngine manual run with custom input values (demonstrated in tests)
- Slots manager for the SearchEngine
- RankByBid2 methodic
- RankByRevenue1 methodic
- RankByRevenue2 methodic
- CTR calculation (weights are equal to 1 for all bidders)
- Exceptions and system messages

Consider the following changes in implemented architecture:
- Better RankBy... methods integration (still, extending Auction abstract class)
- The amount should be related to "current" budget (e.g. per month, quarter, etc.), a separate manager

Despite high test coverage, tests should be developed way further:
- Integration tests should cover all possible interactions and dependencies
- All corner cases should be covered
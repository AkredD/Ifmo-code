
data BinTree e = Empty | Node e (BinTree e) (BinTree e) deriving(Show, Eq, Ord)



instance Foldable BinTree where
    foldMap f Empty = mempty
    foldMap f (Node e Empty Empty) = f e
    foldMap f (Node e l r) = (foldMap f l) `mappend` (f e) `mappend` (foldMap f r)

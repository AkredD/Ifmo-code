
class Monad' m where
  (>>=)  :: m a -> (  a -> m b) -> m b
  return :: a -> m a
  
instance Monad' ((->) r) where 
    f >>= k = \ r -> k (f r) r
    return a = \_ -> a

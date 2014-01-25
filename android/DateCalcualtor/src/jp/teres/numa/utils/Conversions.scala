package jp.teres.numa.utils

import scala.reflect.ClassTag
import android.view._

object Conversions {
	implicit class View2Opt(val view : View) extends AnyVal{
		def asOpt[T <: View](implicit tag : ClassTag[T]) : Option[T] = {
			view match {
				case v : T => Some(v)
				case _ => None
			}
		}
	}
}
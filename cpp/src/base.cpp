#include "base.h"

namespace processingformats {

ProcessingBase::ProcessingBase() {
}


ProcessingBase::~ProcessingBase() {
}

bool ProcessingBase::isValid() {
	std::vector<std::string> errorlist = getErrors();

	if (errorlist.size() == 0) {
		// no errors
		return (true);
	} else {
		return (false);
	}
}
}  // namespace processingformats

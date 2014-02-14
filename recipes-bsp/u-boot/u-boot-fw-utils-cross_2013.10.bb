DESCRIPTION = "U-boot bootloader fw_printenv/setenv utils"
SECTION = "bootloader"
DEPENDS = "mtd-utils"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=025bf9f768cbcb1a165dbe1a110babfb"
SECTION = "bootloader"

# This revision corresponds to the tag "v2013.10"
# We use the revision in order to avoid having to fetch it from the
# repo during parse
SRCREV = "183acb700378a8cfc5d50a01a65de93fb2c24586"

PV = "v2013.10+git${SRCPV}"


SRC_URI = "git://git.denx.de/u-boot.git;branch=master;protocol=git"

S = "${WORKDIR}/git"

inherit uboot-config cross

EXTRA_OEMAKE = 'CROSS_COMPILE=${TARGET_PREFIX} CC="${TARGET_PREFIX}gcc ${TOOLCHAIN_OPTIONS}"'

do_compile () {
  oe_runmake ${UBOOT_MACHINE}
  oe_runmake env
}

do_install () {
  install -d ${D}${bindir_cross}
  install -m 755 ${S}/tools/env/fw_printenv ${D}${bindir_cross}/fw_printenv
  install -m 755 ${S}/tools/env/fw_printenv ${D}${bindir_cross}/fw_setenv
}

SYSROOT_PREPROCESS_FUNCS = "uboot_fw_utils_cross"
uboot_fw_utils_cross() {
    sysroot_stage_dir ${D}${bindir_cross} ${SYSROOT_DESTDIR}${bindir_cross}
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
